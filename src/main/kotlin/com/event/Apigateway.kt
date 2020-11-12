package com.event

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent

/**
 * Class to handle different kind of events from APIGateway Event
 * @see <a href="https://docs.aws.amazon.com/lambda/latest/dg/services-apigateway.html">APIGateway Events</a>
 */
class Apigateway : RequestHandler<APIGatewayProxyRequestEvent?, APIGatewayProxyResponseEvent?> {
    override fun handleRequest(input: APIGatewayProxyRequestEvent?, context: Context?): APIGatewayProxyResponseEvent? {
        context?.logger?.log(input.toString())
        return APIGatewayProxyResponseEvent()
    }
}