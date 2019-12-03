package com.wix.e2e.http.matchers.internal

import com.wix.e2e.http.matchers.ResponseMatchers._
import com.wix.e2e.http.matchers.drivers.HttpResponseFactory._
import com.wix.e2e.http.matchers.drivers.HttpResponseMatchers._
import com.wix.e2e.http.matchers.drivers.{HttpMessageTestSupport, MatchersTestSupport}
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class ResponseCookiesMatchersTest extends AnyWordSpec with MatchersTestSupport {

  trait ctx extends HttpMessageTestSupport

  "ResponseCookiesMatchers" should {

    "match if cookiePair with name is found" in new ctx {
      aResponseWithCookies(cookie) should receivedCookieWith(cookie.name)
    }

    "failure message should describe which cookies are present and which did not match" in new ctx {
      failureMessageFor(receivedCookieWith(cookie.name), matchedOn = aResponseWithCookies(anotherCookie, yetAnotherCookie)) should
        ( include(cookie.name) and include(anotherCookie.name) and include(yetAnotherCookie.name) )
    }

    "failure message for response withoout cookies will print that the response did not contain any cookies" in new ctx {
      receivedCookieWith(cookie.name).apply( aResponseWithNoCookies ).failureMessage should
        include("Response did not contain any `Set-Cookie` headers.")
    }

    "allow to compose matcher with custom cookiePair matcher" in new ctx {
      aResponseWithCookies(cookie) should receivedCookieThat(must = cookieWith(cookie.value))
    }
  }
}
