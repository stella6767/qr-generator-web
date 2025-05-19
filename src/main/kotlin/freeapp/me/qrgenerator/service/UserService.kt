package freeapp.me.qrgenerator.service

import freeapp.me.qrgenerator.config.UserPrincipal
import freeapp.me.qrgenerator.repo.UserRepository
import freeapp.me.qrgenerator.util.clearSecurityContext
import freeapp.me.qrgenerator.web.dto.UpdateProfileDto
import freeapp.me.qrgenerator.web.dto.UserDeleteRequestDto
import freeapp.me.qrgenerator.web.dto.UserResponseDto
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class UserService(
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder
) {

    @Transactional(readOnly = true)
    fun findUserById(userId: Long): UserResponseDto {

        val user = (userRepository.findByIdOrNull(userId)
            ?: throw EntityNotFoundException("user with id $userId not found"))

        return UserResponseDto.fromEntity(user)
    }


    @Transactional
    fun deleteUser(
        userId: Long,
        deleteRequestDto: UserDeleteRequestDto,
    ) {

        val user =
            userRepository.findByIdOrNull(userId) ?: throw EntityNotFoundException("can not find user with id $userId")

        user.delete(deleteRequestDto.reason)

        clearSecurityContext()
    }


    @Transactional
    fun updateUser(
        userId: Long,
        profileDto: UpdateProfileDto
    ): UserResponseDto {

        val user =
            userRepository.findByIdOrNull(userId)
                ?: throw EntityNotFoundException("can not find user with id ${userId}")

        user.update(profileDto.username, encoder.encode(profileDto.password))
        //principal.user = user //세션동기화
        return UserResponseDto.fromEntity(user)
    }


}
