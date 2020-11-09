package com.goticks

import akka.actor.{ActorRef, ActorSystem}
import akka.event.Logging
import com.goticks.BackendMain.configuredRequestTimeout
import com.typesafe.config.ConfigFactory

object FrontendRemoteDeployWatchMain extends App with Startup {
  val config = ConfigFactory.load("frontend-remote-deploy_artery")
  implicit val system = ActorSystem("frontend", config)

  val api = new RestApi() {
    val log = Logging(system.eventStream, "frontend-remote-watch")
    implicit val requestTimeout = configuredRequestTimeout(config)
    implicit def executionContext = system.dispatcher
    def createBoxOffice: ActorRef = {
      system.actorOf(
        RemoteBoxOfficeForwarder.props,
        RemoteBoxOfficeForwarder.name
      )
    }
  }

  startup(api.routes)
}
