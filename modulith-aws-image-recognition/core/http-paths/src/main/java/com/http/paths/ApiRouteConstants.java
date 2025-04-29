package com.http.paths;

/**
 * It’s perfectly fine for an SDK and other microservices to depend on {@code core:http-paths}.
 *
 * This module serves as a central source of truth for shared route constants across microservices,
 * helping avoid duplication, maintain consistency, and simplify maintenance.
 *
 * Benefits:
 * <ul>
 *   <li><b>Centralization:</b> All services and SDKs reference the same route definitions.</li>
 *   <li><b>Decoupling:</b> SDKs don’t define or manage paths - they just use shared constants.</li>
 *   <li><b>Consistency:</b> Changes to paths only need to happen in one place.</li>
 *   <li><b>Clean versioning:</b> Just ensure SDK versions align with changes in path definitions.</li>
 * </ul>
 *
 * The SDK stays lean, avoiding business logic, while {@code core:http-paths} stays focused on
 * exposing constants for secure and consistent cross-module communication.
 *
 * In a modular microservice architecture, each service should define and manage
 * its own set of route constants to ensure clear ownership, avoid tight coupling,
 * and preserve the autonomy of individual services. This pattern is often implemented
 * through dedicated HTTP path constants modules.
 *
 * <h3>Design Example:</h3>
 *
 * <pre>
 * Service A
 * ├── Defines and publishes: com.myorg:service-A-http-paths:1.0.0
 * ├── Controllers, filters, and tests use constants from this module
 *
 * Service A SDK
 * ├── Declares dependency on: com.myorg:service-A-http-paths:1.0.0
 * ├── Builds requests using the same constant definitions as Service A
 *
 * Service B
 * ├── Defines and publishes: com.myorg:service-B-http-paths:1.0.0
 * ├── Maintains its own API constants — never depends on Service A's module
 *
 * Service B SDK
 * ├── Depends on: com.myorg:service-B-http-paths:1.0.0
 * </pre>
 *
 * <h3>Important Notes:</h3>
 * <ul>
 *   <li>Each service owns its constants module and versioning (e.g., <code>service-A-http-paths</code>).</li>
 *   <li>The SDK for a given service may share its HTTP paths module, because SDK and service evolve together.</li>
 *   <li>Other microservices should not consume another service's path module — use OpenAPI or configuration instead.</li>
 *   <li>If two services have a <code>core-http-paths</code> module, they are separate artifacts, not shared globally.</li>
 * </ul>
 *
 * <h3>Benefits:</h3>
 * <ul>
 *   <li>Decouples services from each other</li>
 *   <li>Ensures bounded context and ownership of public API</li>
 *   <li>Minimizes version coordination between microservices</li>
 *   <li>Promotes clear contracts over shared code</li>
 * </ul>
 */

public class ApiRouteConstants {
    public static final String API_V1 = "/api/v1";
    public static final String VIEW = "/view";

    public static class AwsS3 {
        public static final String S3 = "/s3";
        public static final String S3_ALL = S3 + "/all";
        public static final String S3_DELETE = S3 + "/delete/{key}";
        public static final String S3_DOWNLOAD = S3 + "/download/{key}";
        public static final String S3_GENERATE_URL = S3 + "/generate/{key}";
        public static final String S3_UPLOAD = S3 + "/upload";
    }

    public static class AwsRekognition {

        public static final String AWS_REKOGNITION_IDENTIFY = "/images/identify";
    }

    public static class ImageRecognition {

        public static final String IMAGE_RECOGNITION = "/images";
        public static final String IMAGE_RECOGNITION_SEARCH = IMAGE_RECOGNITION + "/search/{key}";
        public static final String IMAGE_RECOGNITION_UPLOAD = IMAGE_RECOGNITION + "/upload";
        public static final String VIEW_IMAGES = "/images";
        public static final String VIEW_IMAGES_UPLOAD = VIEW_IMAGES + "/upload";
    }

}
