package com.fastfood

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import kotlin.jvm.java

@SpringBootApplication
@EntityScan(basePackages = ["com.fastfood"])
object FastFoodApplication {
	@JvmStatic
	fun main(args: Array<String>) {
		SpringApplication.run(FastFoodApplication::class.java, *args)
	}
}
