package ch.frankel.blog.oop

import org.springframework.boot.*
import org.springframework.boot.autoconfigure.*
import org.springframework.context.annotation.*
import org.springframework.context.annotation.aspectj.*

@SpringBootApplication
@EnableSpringConfigured
@EnableLoadTimeWeaving
class OopspringApplication

fun main(args: Array<String>) {
    SpringApplication.run(OopspringApplication::class.java, *args)
}
