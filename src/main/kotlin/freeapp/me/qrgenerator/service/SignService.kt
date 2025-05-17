package freeapp.me.qrgenerator.service

import freeapp.me.qrgenerator.repo.UserRepository
import freeapp.me.qrgenerator.web.dto.SignUpDto
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class SignService(
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        TODO("Not yet implemented")
    }


    @Transactional
    fun signUp(signUpDto: SignUpDto) {




    }


}
