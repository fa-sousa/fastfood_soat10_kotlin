package com.fastfood

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import kotlin.jvm.java

@SpringBootApplication
object FastfoodApplication {
	@JvmStatic
	fun main(args: Array<String>) {
		SpringApplication.run(FastfoodApplication::class.java, *args)
	}
}
