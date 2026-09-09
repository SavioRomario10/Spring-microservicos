package io.savioromario10.msavaliadorcredito.infra.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "msclientes", name = "/clientes")
public interface ClienteResourceClient {

}