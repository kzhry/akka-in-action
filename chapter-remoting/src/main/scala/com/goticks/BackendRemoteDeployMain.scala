package com.goticks

import com.typesafe.config.ConfigFactory
import akka.actor.ActorSystem

object BackendRemoteDeployMain extends App {
  val config = ConfigFactory.load("backend_artery")
  val system = ActorSystem("backend", config)
}
