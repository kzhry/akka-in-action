package com.goticks

import akka.actor.{ActorRef, ActorSystem}
import akka.event.Logging
import com.goticks.BackendMain.configuredRequestTimeout
import com.typesafe.config.ConfigFactory

object SingleNodeMain extends App with Startup {
  val config = ConfigFactory.load("singlenode")
  implicit val system = ActorSystem("singlenode", config)

  /*
    RestApiはトレイトなので直接インスタンス化はできない．x new RestApi
    new RestApi() {...} はRestApiの匿名クラスを作ってインスタンス化する構文
   */
  val api = new RestApi() {
    // 抽象メンバーの実装
    val log = Logging(system.eventStream, "go-ticks")
    implicit val requestTimeout = configuredRequestTimeout(config)
    implicit def executionContext = system.dispatcher
    def createBoxOffice: ActorRef =
      system.actorOf(BoxOffice.props, BoxOffice.name)
  }

  startup(api.routes)
}
