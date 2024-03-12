package org.splitec.client;

import com.azure.cosmos.ConsistencyLevel;
import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.CosmosContainer;
import com.azure.cosmos.CosmosDatabase;
import com.azure.cosmos.implementation.ConflictException;
import com.azure.cosmos.models.CosmosItemRequestOptions;
import com.azure.cosmos.models.CosmosQueryRequestOptions;
import com.azure.cosmos.models.PartitionKey;
import com.azure.cosmos.util.CosmosPagedIterable;
import org.splitec.config.CosmosConfig;
import org.splitec.model.User;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseClient {

    private static final Logger logger = LogManager.getLogger(DatabaseClient.class);
    private CosmosClient client;
    private CosmosDatabase database;
    private CosmosContainer container;

    public void buildClient() {
        try {
            logger.info("Using Azure Cosmos DB endpoint: " + CosmosConfig.HOST);

            ArrayList<String> preferredRegions = new ArrayList<>();
            preferredRegions.add("West US");

            client = new CosmosClientBuilder()
                    .endpoint(CosmosConfig.HOST)
                    .key(CosmosConfig.MASTER_KEY)
                    .preferredRegions(preferredRegions)
                    .userAgentSuffix("CosmosDBJavaQuickstart")
                    .consistencyLevel(ConsistencyLevel.EVENTUAL)
                    .buildClient();

            fillDatabase();
            fillContainer();
        }
        catch (Exception e) {
            logger.error("Cosmos getStarted failed with %s%n", e);
            throw e;
        }
    }

    private void fillDatabase() {
        try {
            database = client.getDatabase(CosmosConfig.DATABASE_NAME);
            logger.info("Checking database " + database.getId() + " completed!\n");
        }
        catch (Exception e) {
            logger.error("Error to fill database: ", e);
            throw e;
        }
    }

    private void fillContainer() {
        try {
            container = database.getContainer(CosmosConfig.CONTAINER_NAME);
            logger.info("Checking container " + container.getId() + " completed!\n");
        }
        catch (Exception e) {
            logger.error("Error to fill container: ", e);
            throw e;
        }
    }

    public User getUser (String id) {

        User user = null;
        this.buildClient();

        try {
            CosmosQueryRequestOptions queryOptions = new CosmosQueryRequestOptions();
            queryOptions.setQueryMetricsEnabled(true);

            String query = String.format(
                    "SELECT * FROM %s a WHERE a.id = '%s'",
                    CosmosConfig.DATABASE_NAME,
                    id
            );

            CosmosPagedIterable<User> userPagedIterable = container.queryItems(query, queryOptions, User.class);

            if(userPagedIterable.stream().findAny().isPresent()){
                AtomicReference<User> userReference = new AtomicReference<>();
                userPagedIterable.iterableByPage().forEach(cosmosItemPropertiesFeedResponse -> {
                    userReference.set(cosmosItemPropertiesFeedResponse
                            .getResults().get(0));
                });
                user = userReference.get();
            }
        }
        catch (Exception e) {
            logger.error("Error to get User: ", e);
            client.close();
            throw e;
        }
        client.close();
        return user;
    }

    public void InsertUser(User user) {

        this.buildClient();

        try {
            container.createItem(user.getId(), new PartitionKey(user.getSkinType()), new CosmosItemRequestOptions());
        } catch (ConflictException e) {
            logger.error("User already exists : ", e);
            throw e;
        } catch (Exception e) {
            logger.error("Error to insert User: ", e);
            throw e;
        } finally {
            client.close();
        }
    }
}


