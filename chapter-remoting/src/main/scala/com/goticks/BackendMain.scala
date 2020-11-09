package com.goticks

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory

object BackendMain extends App with RequestTimeout {
  val config = ConfigFactory.load("backend_artery")
  val system = ActorSystem("backend", config)
  implicit val requestTimeout = configuredRequestTimeout(config)
  system.actorOf(BoxOffice.props, BoxOffice.name)
}
