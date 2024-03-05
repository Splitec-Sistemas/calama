package org.splitec.client;

import com.azure.cosmos.ConsistencyLevel;
import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.CosmosContainer;
import com.azure.cosmos.CosmosDatabase;
import com.azure.cosmos.models.CosmosContainerProperties;
import com.azure.cosmos.models.CosmosContainerResponse;
import com.azure.cosmos.models.CosmosDatabaseResponse;
import  org.splitec.config.CosmosConfig;
import java.util.ArrayList;

public class DatabaseClient {

    private CosmosClient client;

    private final String databaseName = "CalamaDB";
    private final String containerName = "Users";

    private CosmosDatabase database;
    private CosmosContainer container;

    public void close() {
        client.close();
    }

    public String getDatabaseName() {
        return databaseName;
    }


    public CosmosContainer getContainer() {
        return container;
    }

    public void buildClient() {
        try {
            System.out.println("Using Azure Cosmos DB endpoint: " + CosmosConfig.HOST);

            ArrayList<String> preferredRegions = new ArrayList<>();
            preferredRegions.add("West US");

            client = new CosmosClientBuilder()
                    .endpoint(CosmosConfig.HOST)
                    .key(CosmosConfig.MASTER_KEY)
                    .preferredRegions(preferredRegions)
                    .userAgentSuffix("CosmosDBJavaQuickstart")
                    .consistencyLevel(ConsistencyLevel.EVENTUAL)
                    .buildClient();

            createDatabaseIfNotExists();
            createContainerIfNotExists();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.printf("Cosmos getStarted failed with %s%n", e);
        }
    }

    private void createDatabaseIfNotExists() {
        System.out.println("Create database " + databaseName + " if not exists.");

        //  Create database if not exists
        CosmosDatabaseResponse databaseResponse = client.createDatabaseIfNotExists(databaseName);
        database = client.getDatabase(databaseResponse.getProperties().getId());

        System.out.println("Checking database " + database.getId() + " completed!\n");
    }

    private void createContainerIfNotExists() {
        System.out.println("Create container " + containerName + " if not exists.");

        //  Create container if not exists
        CosmosContainerProperties containerProperties =
                new CosmosContainerProperties(containerName, "/skinType");

        CosmosContainerResponse containerResponse = database.createContainerIfNotExists(containerProperties);
        container = database.getContainer(containerResponse.getProperties().getId());

        System.out.println("Checking container " + container.getId() + " completed!\n");
    }
}


