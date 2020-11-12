package com.event


import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent


/**
* Class to handle different kind of EventBridge Event types:
* @see <a href="https://docs.aws.amazon.com/eventbridge/latest/userguide/what-is-amazon-eventbridge.html">EventBridge Events</a>
* @see <a href="https://docs.aws.amazon.com/AmazonCloudWatch/latest/events/ScheduledEvents.html">CloudWatch Events</a>
 * */

class EventBridge: RequestHandler<ScheduledEvent,Unit>{
    override fun handleRequest(input: ScheduledEvent?, context: Context?){
        context?.logger?.log(input?.toString())
    }
}