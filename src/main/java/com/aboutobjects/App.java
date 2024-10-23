package com.aboutobjects;


import software.amazon.awssdk.services.timestreamquery.TimestreamQueryClient;
import software.amazon.awssdk.services.timestreamquery.model.QueryRequest;
import software.amazon.awssdk.services.timestreamquery.model.QueryResponse;
import software.amazon.awssdk.services.timestreamquery.model.QueryExecutionException;
import software.amazon.awssdk.regions.Region;

//export AWS_ACCESS_KEY_ID
//export AWS_SECRET_ACCESS_KEY

public class App  {

    private static final String DATABASE_NAME = "myFirstDatabase";
    private static final String TABLE_NAME = "Norman1";
    private static final String REGION = "us-east-1";

    public static void main(String[] args) {

        System.out.println("AWS_ACCESS_KEY_ID: " + System.getenv("AWS_ACCESS_KEY_ID"));
        System.out.println("AWS_SECRET_ACCESS_KEY: " + System.getenv("AWS_SECRET_ACCESS_KEY"));

        // Creating TimestreamQueryClient
        try (TimestreamQueryClient timestreamQueryClient = TimestreamQueryClient.builder()
                .region(Region.of(REGION))
                .build()) {

            // A simple query that returns the first element in the table
            String query_string = String.format("SELECT * FROM %s.%s LIMIT 1", DATABASE_NAME, TABLE_NAME);

            // Creating QueryRequest
            QueryRequest query = QueryRequest.builder()
                    .queryString(query_string)
                    .build();

            try {
                // Execute the query
                QueryResponse first_element = timestreamQueryClient.query(query);

                // Process the query response
                System.out.println("The first Item in the Table " + TABLE_NAME + " :" + first_element.toString());

            } catch (QueryExecutionException e) {
                System.err.println("Error: " + e);
            }
        }
    }
}


