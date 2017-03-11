package controllers

import play.api.mvc._
import scala.concurrent._
class TestController extends Controller {
def neverFinish = Action.async { Future.never }
}
