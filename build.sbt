scalaVersion := "2.12.1"
enablePlugins(PlayScala)
libraryDependencies += "com.typesafe.play" %% "play-netty-server" % "2.6.0-M2"

if ( sys.props.contains("use-netty") )
List(PlayKeys.devSettings += "play.server.provider" -> "play.core.server.NettyServerProvider")
else Nil
