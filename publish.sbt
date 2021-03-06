licenses := Seq("Apache-2.0" -> url("http://www.opensource.org/licenses/Apache-2.0"))

homepage := Some(url("http://github.com/typelevel/scalaz-scalatest"))

publishMavenStyle := true

releaseCrossBuild := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

useGpg := true

usePgpKeyHex("E84BBF42")

pomExtra := (
    <scm>
      <url>git@github.com:typelevel/scalaz-scalatest.git</url>
      <connection>scm:git@github.com:typelevel/scalaz-scalatest.git</connection>
    </scm>
    <developers>
      {
      Seq(
        ("coltfred", "Colt Frederickson"),
        ("bwmcadams", "Brendan McAdams")
      ).map {
        case (id, name) =>
          <developer>
            <id>{id}</id>
            <name>{name}</name>
            <url>http://github.com/{id}</url>
          </developer>
      }
    }
    </developers>
  )

import ReleaseTransformations._

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  ReleaseStep(action = Command.process("publishSigned", _), enableCrossBuild = true),
  setNextVersion,
  commitNextVersion,
  ReleaseStep(action = Command.process("sonatypeReleaseAll", _), enableCrossBuild = true),
  pushChanges
)
