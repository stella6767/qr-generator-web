@import freeapp.me.qrgenerator.web.dto.UserResponseDto

@param UserResponseDto currentUser

@template.layout.main(content = @`
    @template.component.header()
    <main id="main-container"
          class="bg-gray-50 min-h-screen flex flex-col items-center">
        @template.component.auth.profileBox(currentUser = currentUser)
    </main>

`)
