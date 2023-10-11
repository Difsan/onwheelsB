package co.com.onwheels.onwheelsb.infrastructure.entrypoints;

import co.com.onwheels.onwheelsb.domain.model.product.Product;
import co.com.onwheels.onwheelsb.domain.usecase.product.deleteproduct.DeleteProductUseCase;
import co.com.onwheels.onwheelsb.domain.usecase.product.getallproducts.GetAllProductsUseCase;
import co.com.onwheels.onwheelsb.domain.usecase.product.getproductbycategory.GetProductByCategoryUseCase;
import co.com.onwheels.onwheelsb.domain.usecase.product.getproductbyid.GetProductByIdUseCase;
import co.com.onwheels.onwheelsb.domain.usecase.product.getproductbyname.GetProductByNameUseCase;
import co.com.onwheels.onwheelsb.domain.usecase.product.getproductsbymodel.GetProductsByModelUseCase;
import co.com.onwheels.onwheelsb.domain.usecase.product.getproductsbyunitaryprice.GetProductsByUnitaryPriceUseCase;
import co.com.onwheels.onwheelsb.domain.usecase.product.saveproduct.SaveProductUseCase;
import co.com.onwheels.onwheelsb.domain.usecase.product.updateproduct.UpdateProductUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRestProduct {

    @Bean
    @RouterOperation(path = "/products", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetAllProductsUseCase.class, method = RequestMethod.GET,
            beanMethod = "get",
            operation = @Operation(operationId = "getAllProducts", tags = "Product usecases",
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Success",
                                    content = @Content(schema = @Schema(implementation = Product.class))),
                            @ApiResponse(responseCode = "204", description = "Nothing to show")
                    }))
    public RouterFunction<ServerResponse> getAllProducts(GetAllProductsUseCase getAllProductsUseCase) {
        return route(GET("/products"),
                request -> {
                    Flux<Product> productFlux = getAllProductsUseCase.get();
                    return productFlux
                            .collectList()
                            .flatMap(products -> {
                                if (products.isEmpty()) {
                                    return ServerResponse.status(HttpStatus.NO_CONTENT).bodyValue("No products available for show");
                                } else {
                                    return ServerResponse.ok()
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .bodyValue(products);
                                }
                            })
                            .onErrorResume(throwable -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue("Error occurred: " + throwable.getMessage()));
                });
    }

    @Bean
    @RouterOperation(path = "/products/byId/{productId}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetProductByIdUseCase.class,
            method = RequestMethod.GET,
            beanMethod = "apply",
            operation = @Operation(operationId = "getProductById", tags = "Product usecases",
                    parameters = {@Parameter(name = "productId", description = "product Id", required= true, in = ParameterIn.PATH)},
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Success",
                                    content = @Content (schema = @Schema(implementation = Product.class))),
                            @ApiResponse(responseCode = "404", description = "Not Found")
                    }))
    public RouterFunction<ServerResponse> getProductsById (GetProductByIdUseCase getProductByIdUseCase){
        return route(GET("/products/byId/{productId}"),
                request -> getProductByIdUseCase.apply(request.pathVariable("productId"))
                        .flatMap(product -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(product))
                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_FOUND)
                                .bodyValue(throwable.getMessage()))
        );
    }

    @Bean
    @RouterOperation(path = "/products/byName/{productName}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetProductByNameUseCase.class,
            method = RequestMethod.GET,
            beanMethod = "apply",
            operation = @Operation(operationId = "getProductByName", tags = "Product usecases",
                    parameters = {@Parameter(name = "productName", description = "product Name", required= true, in = ParameterIn.PATH)},
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Success",
                                    content = @Content (schema = @Schema(implementation = Product.class))),
                            @ApiResponse(responseCode = "404", description = "Not Found")
                    }))
    public RouterFunction<ServerResponse> getProductsByName (GetProductByNameUseCase getProductByNameUseCase){
        return route(GET("/products/byName/{productName}"), request -> {
            Flux<Product> productFlux = getProductByNameUseCase.apply(request.pathVariable("productName"));
            return productFlux
                    .collectList()
                    .flatMap(products -> {
                        if (products.isEmpty()){
                            return ServerResponse.status(HttpStatus.NOT_FOUND).bodyValue("No products found");
                        } else {
                            return ServerResponse.ok()
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .bodyValue(products);
                        }
                    })
                    .onErrorResume(throwable -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue("Error occurred: " + throwable.getMessage()));
                }
        );
    }

    @Bean
    @RouterOperation(path = "/products/byModel/{model}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetProductsByModelUseCase.class,
            method = RequestMethod.GET,
            beanMethod = "apply",
            operation = @Operation(operationId = "getProductsByModel", tags = "Product usecases",
                    parameters = {@Parameter(name = "model", description = "product model", required= true, in = ParameterIn.PATH)},
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Success",
                                    content = @Content (schema = @Schema(implementation = Product.class))),
                            @ApiResponse(responseCode = "404", description = "Not Found")
                    }))
    public RouterFunction<ServerResponse> getProductsByModel (GetProductsByModelUseCase getProductsByModelUseCase){
        return route(GET("/products/byModel/{model}"), request -> {
                    Flux<Product> productFlux = getProductsByModelUseCase.apply(request.pathVariable("model"));
                    return productFlux
                            .collectList()
                            .flatMap(products -> {
                                if (products.isEmpty()){
                                    return ServerResponse.status(HttpStatus.NOT_FOUND).bodyValue("No products found");
                                } else {
                                    return ServerResponse.ok()
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .bodyValue(products);
                                }
                            })
                            .onErrorResume(throwable -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue("Error occurred: " + throwable.getMessage()));
                }
        );
    }

    @Bean
    @RouterOperation(path = "/products/byCategory/{productCategory}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetProductByCategoryUseCase.class,
            method = RequestMethod.GET,
            beanMethod = "apply",
            operation = @Operation(operationId = "getProductByCategory", tags = "Product usecases",
                    parameters = {@Parameter(name = "productCategory", description = "product Category", required= true, in = ParameterIn.PATH)},
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Success",
                                    content = @Content (schema = @Schema(implementation = Product.class))),
                            @ApiResponse(responseCode = "404", description = "Not Found")
                    }))
    public RouterFunction<ServerResponse> getProductsByCategory (GetProductByCategoryUseCase getProductByCategoryUseCase){
        return route(GET("/products/byCategory/{productCategory}"), request -> {
                    Flux<Product> productFlux = getProductByCategoryUseCase.apply(request.pathVariable("productCategory"));
                    return productFlux
                            .collectList()
                            .flatMap(products -> {
                                if (products.isEmpty()){
                                    return ServerResponse.status(HttpStatus.NOT_FOUND).bodyValue("No products found");
                                } else {
                                    return ServerResponse.ok()
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .bodyValue(products);
                                }
                            })
                            .onErrorResume(throwable -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue("Error occurred: " + throwable.getMessage()));
                }
        );
    }

    @Bean
    @RouterOperation(path = "/products/byUnitaryPrice/{productPrice}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetProductsByUnitaryPriceUseCase.class,
            method = RequestMethod.GET,
            beanMethod = "apply",
            operation = @Operation(operationId = "getProductByUnitaryPriceUseCase", tags = "Product usecases",
                    parameters = {@Parameter(name = "productPrice", description = "product unitary price", required= true, in = ParameterIn.PATH)},
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Success",
                                    content = @Content (schema = @Schema(implementation = Product.class))),
                            @ApiResponse(responseCode = "404", description = "Not Found")
                    }))
    public RouterFunction<ServerResponse> getProductsByUnitaryPrice (GetProductsByUnitaryPriceUseCase getProductsByUnitaryPriceUseCase){
        return route(GET("/products/byUnitaryPrice/{productPrice}"), request -> {
                    Double price = Double.parseDouble(request.pathVariable("productPrice"));
                    Flux<Product> productFlux = getProductsByUnitaryPriceUseCase.apply(price);
                    return productFlux
                            .collectList()
                            .flatMap(products -> {
                                if (products.isEmpty()){
                                    return ServerResponse.status(HttpStatus.NOT_FOUND).bodyValue("No products found");
                                } else {
                                    return ServerResponse.ok()
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .bodyValue(products);
                                }
                            })
                            .onErrorResume(throwable -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue("Error occurred: " + throwable.getMessage()));
                }
        );
    }


    @Bean
    @RouterOperation(path = "/products", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = SaveProductUseCase.class, method = RequestMethod.POST,
            beanMethod = "apply",
            operation = @Operation(operationId = "saveProduct", tags = "Product usecases",
                    parameters = {@Parameter(name = "product", in = ParameterIn.PATH,
                            schema = @Schema(implementation = Product.class))},
                    responses = {
                            @ApiResponse(responseCode = "201", description = "Success",
                                    content = @Content (schema = @Schema(implementation = Product.class))),
                            @ApiResponse(responseCode = "406", description = "Not acceptable, Try again")
                    },
                    requestBody = @RequestBody(required = true, description = "Save a Product following the schema",
                            content = @Content(schema = @Schema(implementation = Product.class)))
            ))
    public RouterFunction<ServerResponse> saveProduct (SaveProductUseCase saveProductUseCase){
        return route(POST("/products").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Product.class)
                        .flatMap(product -> saveProductUseCase.apply(product)
                                .flatMap(result -> ServerResponse.status(201)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                                .onErrorResume(throwable -> ServerResponse.status(HttpStatus.BAD_REQUEST)
                                        .bodyValue(throwable.getMessage()))));
    }

    @Bean
    @RouterOperation(path = "/products/{productId}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = UpdateProductUseCase.class, method = RequestMethod.PUT,
            beanMethod = "apply",
            operation = @Operation(operationId = "updateProduct", tags = "Product usecases",
                    parameters = {@Parameter(name = "productId", description = "product Id", required= true, in = ParameterIn.PATH),
                            @Parameter(name = "product", in = ParameterIn.PATH,
                                    schema = @Schema(implementation = Product.class))},
                    responses = {
                            @ApiResponse(responseCode = "201", description = "Success",
                                    content = @Content (schema = @Schema(implementation = Product.class))),
                            @ApiResponse(responseCode = "406", description = "Not acceptable, Try again")
                    },
                    requestBody = @RequestBody(required = true, description = "Update a Product following the schema",
                            content = @Content(schema = @Schema(implementation = Product.class)))
            ))
    public RouterFunction<ServerResponse> updateProduct (UpdateProductUseCase updateProductUseCase){
        return route(PUT("/products/{productId}").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Product.class)
                        .flatMap(product -> updateProductUseCase.apply(request.pathVariable("productId"),
                                        product)
                                .flatMap(result -> ServerResponse.status(201)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                                .onErrorResume(throwable -> ServerResponse.status(HttpStatus.BAD_REQUEST)
                                        .bodyValue(throwable.getMessage()))
                        )
        );
    }

    @Bean
    @RouterOperation(path = "/products/{productId}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = DeleteProductUseCase.class, method = RequestMethod.DELETE,
            beanMethod = "apply",
            operation = @Operation(operationId = "deleteProductById", tags = "Product usecases",
                    parameters = {@Parameter(name = "productId", description = "product Id", required= true, in = ParameterIn.PATH)},
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Success",
                                    content = @Content (schema = @Schema(implementation = Product.class))),
                            @ApiResponse(responseCode = "404", description = "Not Found")
                    }))
    public RouterFunction<ServerResponse> deleteProduct (DeleteProductUseCase deleteProductUseCase){
        return route(DELETE("/products/{productId}"),
                request -> deleteProductUseCase.apply(request.pathVariable("productId"))
                        .thenReturn(ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue("Product deleted"))
                        .flatMap(serverResponseMono -> serverResponseMono)
                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_FOUND).bodyValue(throwable.getMessage()))
        );
    }
}
