package org.splitec.service;

import com.azure.cosmos.CosmosContainer;
import com.azure.cosmos.models.CosmosQueryRequestOptions;
import com.azure.cosmos.util.CosmosPagedIterable;
import org.splitec.client.DatabaseClient;
import org.splitec.model.User;

import java.util.concurrent.atomic.AtomicReference;

public class CosmosService {

    public User getUser (String id) {

        DatabaseClient databaseClient = new DatabaseClient();
        User user = null;

        try {
            databaseClient.buildClient();
            CosmosQueryRequestOptions queryOptions = new CosmosQueryRequestOptions();
            queryOptions.setQueryMetricsEnabled(true);

            String query = String.format(
                    "SELECT * FROM %s a WHERE a.id = '%s'",
                    databaseClient.getDatabaseName(),
                    id
            );

            CosmosContainer usersContainer = databaseClient.getContainer();
            CosmosPagedIterable<User> userPagedIterable = usersContainer.queryItems(query, queryOptions, User.class);
            AtomicReference<User> userReference = new AtomicReference<>();

            userPagedIterable.iterableByPage().forEach(cosmosItemPropertiesFeedResponse -> {
                userReference.set(cosmosItemPropertiesFeedResponse
                        .getResults().get(0));
            });
            user = userReference.get();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.printf("Cosmos getStarted failed with %s%n", e);
        }
        finally {
            System.out.println("Closing the client");
            databaseClient.close();
        }
        return user;
    }
}
