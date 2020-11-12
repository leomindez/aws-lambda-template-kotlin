package com.event

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler

/**
 * Class to handle DynamoDBStreams
 * @see <a href="https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Streams.LowLevel.Walkthrough.html">DynamoDB Stream</a>
 * */

class DynamoDBStream: RequestHandler<DynamoDBStream,Unit> {
    override fun handleRequest(input: DynamoDBStream?, context: Context?){
        context?.logger?.log(input.toString())
    }
}