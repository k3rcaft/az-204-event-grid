package com.k3rcaft.galaxycinemafa;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.Cardinality;
import com.microsoft.azure.functions.annotation.EventHubTrigger;
import com.microsoft.azure.functions.annotation.FunctionName;

import java.util.List;

/**
 * Azure Functions with Event Hub trigger.
 */
public class CinemaTriggerFunction {
    /**
     * This function will be invoked when an event is received from Event Hub.
     */
    @FunctionName("cartoon-hub-trigger")
    public void cartoonHubTrigger(
            @EventHubTrigger(name = "message",
                    eventHubName = "cartoon",
                    connection = "EVENT_HUB_CONNECTION_STRING",
                    cardinality = Cardinality.MANY)
                    List<String> message,
            final ExecutionContext context
    ) {
        context.getLogger().info("cartoon-hub-trigger function executed.");
        context.getLogger().info("Length:" + message.size());
        message.forEach(singleMessage -> context.getLogger().info(singleMessage));
    }

    @FunctionName("action-hub-trigger")
    public void actionHubTrigger(
            @EventHubTrigger(name = "message",
                    eventHubName = "action",
                    connection = "EVENT_HUB_CONNECTION_STRING",
                    cardinality = Cardinality.MANY)
                    List<String> message,
            final ExecutionContext context
    ) {
        context.getLogger().info("action-hub-trigger function executed.");
        context.getLogger().info("Length:" + message.size());
        message.forEach(singleMessage -> context.getLogger().info(singleMessage));
    }
}
