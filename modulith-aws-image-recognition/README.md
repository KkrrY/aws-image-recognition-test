Modulith Application
Overview

The Modulith application is designed using a modular monolith architecture, combining the simplicity of a monolith with the flexibility and organization of modularity. This application separates various concerns into well-defined modules, which can evolve and scale independently within the monolithic structure.

Each module in the application has a clear responsibility, and modules interact with each other through well-defined interfaces. This architecture provides a solid foundation for building complex systems while maintaining simplicity, cohesion, and easier maintainability.

Such architecture is primarily used to switch to microservice architecture quickly, only adding logic to -api modules (who will act as SDK with their own DTO layer to ensure backward-compatibility), which will be responsible for client communication with corresponding microservice(via feign client/rest/gRPC, whatever).

Getting Started
Prerequisites

To run the application locally, make sure you have the following installed:

    Java 17 (or higher)

     Gradle

    Docker (optional, for containerized deployments)