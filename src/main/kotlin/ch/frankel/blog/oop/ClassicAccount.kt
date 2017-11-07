package ch.frankel.blog.oop

import org.springframework.data.repository.*
import org.springframework.stereotype.*
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal
import javax.persistence.*

@RestController
class ClassicAccountController(private val service: AccountService) {

    @GetMapping("/classicaccount/{iban}")
    fun getAccount(@PathVariable("iban") iban: String) = service.findAccount(iban)
}

@Service
class AccountService(private val repository: ClassicAccountRepository) {
    fun findAccount(iban: String): ClassicAccount? {
        checkIban(iban)
        return repository.findOne(iban)
    }

    fun checkIban(iban: String) {
        if (iban.isBlank()) throw IllegalArgumentException("IBAN cannot be blank")
    }
}

interface ClassicAccountRepository : CrudRepository<ClassicAccount, String>

@Entity
@Table(name = "ACCOUNT")
class ClassicAccount(@Id var iban: String = "", var balance: BigDecimal = BigDecimal.ZERO)