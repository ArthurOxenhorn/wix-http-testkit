package com.wix.e2e.http.matchers.internal

import akka.http.scaladsl.model.ContentTypes._
import com.wix.e2e.http.matchers.RequestMatchers._
import com.wix.e2e.http.matchers.drivers.HttpRequestFactory._
import com.wix.e2e.http.matchers.drivers.{HttpMessageTestSupport, MatchersTestSupport}
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class RequestContentTypeMatchersTest extends AnyWordSpec with MatchersTestSupport {

  trait ctx extends HttpMessageTestSupport

  "RequestContentTypeMatchers" should {

    "exact match on request json content type" in new ctx {
      aRequestWith(`application/json`) should haveJsonBody
      aRequestWith(`text/csv(UTF-8)`) should not( haveJsonBody )
    }

    "exact match on request text plain content type" in new ctx {
      aRequestWith(`text/plain(UTF-8)`) should haveTextPlainBody
      aRequestWith(`text/csv(UTF-8)`) should not( haveTextPlainBody )
    }

    "exact match on request form url encoded content type" in new ctx {
      aRequestWith(`application/x-www-form-urlencoded`) should haveFormUrlEncodedBody
      aRequestWith(`text/csv(UTF-8)`) should not( haveFormUrlEncodedBody )
    }

    "exact match on multipart request content type" in new ctx {
      aRequestWith(`multipart/form-data`) should haveMultipartFormBody
      aRequestWith(`text/csv(UTF-8)`) should not( haveMultipartFormBody )
    }
  }
}
