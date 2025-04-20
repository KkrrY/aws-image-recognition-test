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
 */

public class ApiRouteConstants {
    public static final String API_V1 = "/api/v1";

    public static final String S3 = "/s3";
    public static final String S3_UPLOAD = S3 + "/upload";
    public static final String S3_GENERATE_URL = S3 + "/generate/{key}";
    public static final String S3_DOWNLOAD = S3 + "/download/{key}";
    public static final String S3_DELETE = S3 + "/delete/{key}";
    public static final String S3_ALL = S3 + "/all";

    public static final String AWS_REKOGNITION_IDENTIFY = "/images/identify";

    public static final String IMAGE_RECOGNITION = "/images";
    public static final String IMAGE_RECOGNITION_UPLOAD = IMAGE_RECOGNITION + "/upload";
    public static final String IMAGE_RECOGNITION_SEARCH = IMAGE_RECOGNITION + "/search/{key}";

}
