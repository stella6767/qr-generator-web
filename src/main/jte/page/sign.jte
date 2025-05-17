@param Boolean isSignUp

@template.layout.main(content = @`

    @template.component.header()
    <main class="bg-gray-50 min-h-screen flex flex-col items-center">
        @template.component.auth.signBox(isSignUp = isSignUp)
    </main>

`)

