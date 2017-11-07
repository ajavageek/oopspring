package ch.frankel.blog.oop

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.beans.factory.annotation.*
import org.springframework.data.repository.*
import org.springframework.web.bind.annotation.*
import java.io.Serializable
import java.math.BigDecimal
import javax.persistence.*

@RestController
class OopAccountController {

    @GetMapping("/oopaccount/{iban}")
    fun getAccount(@PathVariable("iban") number: String): OopAccount? {
        val iban = Iban(number)
        return iban.account
    }
}

interface OopAccountRepository : CrudRepository<OopAccount, Iban>

@Entity
@Table(name = "ACCOUNT")
class OopAccount(@EmbeddedId var iban: Iban, var balance: BigDecimal)

@Configurable(autowire = Autowire.BY_TYPE)
class Iban(@Column(name = "iban") val number: String) : Serializable {

    @Transient
    @Autowired
    private lateinit var repository: OopAccountRepository

    init {
        if (number.isBlank()) throw IllegalArgumentException("IBAN cannot be blank")
    }

    val account: OopAccount?
        @JsonIgnore
        get() = repository.findOne(this)
}