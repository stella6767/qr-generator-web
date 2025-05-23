@param Boolean isSignUp

!{
    var url = "";
    if (isSignUp) {
        url = "/sign-up";
    }else {
        url = "/login";
    }
}



<div class="flex-grow flex items-center justify-center w-full px-4">
    <div class="bg-white rounded-lg shadow-sm max-w-md w-full p-8">
        <h1 class="text-2xl font-semibold text-gray-800 mb-2 text-center">
            @if(isSignUp)
                Create an account
            @else
                Log in to your account
            @endif
        </h1>
        <p class="text-gray-600 mb-6 text-center">
            @if(isSignUp)
                It's free and only takes a few seconds.
            @else
                Use your email and password to access your account.
            @endif
        </p>

        <form class=""
              hx-post="${url}"
              hx-target="#main-container"
              hx-disable-elt="#submit-btn"
        >
            <div class="mb-1">
                <label for="email" class="sr-only">Email</label>
                <div class="relative">
                    <input
                            required
                            type="email"
                            id="email"
                            name="email"
                            placeholder="Enter email"
                            class="w-full px-4 py-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent"
                    >
                </div>
            </div>

            <p class="text-gray-500 text-sm mb-2 mt-2">
                Please include an '@' in the email address
            </p>

            <div class="mb-1">
                <label for="password" class="sr-only">Password</label>
                <div class="relative">
                    <input
                            required
                            type="password"
                            id="password"
                            name="password"
                            placeholder="Enter password"
                            class="w-full px-4 py-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent"
                    >
                </div>
            </div>

            <button
                    id="submit-btn"
                    type="submit"
                    class="cursor-pointer w-full bg-emerald-500 text-white py-3 rounded-md hover:bg-emerald-600 transition duration-200 mb-6 mt-4"
            >
                @if(isSignUp)
                    Sign Up
                @else
                    Log in
                @endif
                @template.component.loadingSpinner()
            </button>
        </form>

        <div class="flex items-center">
            <div class="flex-grow h-px bg-gray-200"></div>
            <div class="px-4 text-gray-500">or</div>
            <div class="flex-grow h-px bg-gray-200"></div>
        </div>

        <!-- 소셜 로그인 버튼들 -->
        <div class="flex flex-col space-y-2 mt-2">
            <!-- Google 로그인 버튼 -->
            <a href="/oauth2/authorization/google"
               class="w-full h-10 flex items-center justify-center bg-white border border-gray-300 rounded-md hover:bg-gray-50 transition duration-200 shadow-sm">
                <!-- Google 로고 (SVG로 추가) -->
                <span class="flex items-center">
                    <svg width="18" height="18" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 48 48">
                        <path fill="#EA4335"
                              d="M24 9.5c3.54 0 6.71 1.22 9.21 3.6l6.85-6.85C35.9 2.38 30.47 0 24 0 14.62 0 6.51 5.38 2.56 13.22l7.98 6.19C12.43 13.72 17.74 9.5 24 9.5z"/>
                        <path fill="#4285F4"
                              d="M46.98 24.55c0-1.57-.15-3.09-.38-4.55H24v9.02h12.94c-.58 2.96-2.26 5.48-4.78 7.18l7.73 6c4.51-4.18 7.09-10.36 7.09-17.65z"/>
                        <path fill="#FBBC05"
                              d="M10.53 28.59c-.48-1.45-.76-2.99-.76-4.59s.27-3.14.76-4.59l-7.98-6.19C.92 16.46 0 20.12 0 24c0 3.88.92 7.54 2.56 10.78l7.97-6.19z"/>
                        <path fill="#34A853"
                              d="M24 48c6.48 0 11.93-2.13 15.89-5.81l-7.73-6c-2.15 1.45-4.92 2.3-8.16 2.3-6.26 0-11.57-4.22-13.47-9.91l-7.98 6.19C6.51 42.62 14.62 48 24 48z"/>
                        <path fill="none" d="M0 0h48v48H0z"/>
                    </svg>
                    <span class="ml-2 text-gray-700 font-medium">
                        @if(isSignUp)
                            Sign Up with Google
                        @else
                            Log in with Google
                        @endif
                    </span>
                </span>
            </a>


            <!-- GitHub 로그인 버튼 -->
            <a href="/oauth2/authorization/github"
               class="w-full h-10 flex items-center justify-center bg-white border border-gray-300 rounded-md hover:bg-gray-50 transition duration-200 shadow-sm">
                <span class="flex items-center">
                    <!-- GitHub 아이콘 -->
                    <svg width="18" height="18" fill="currentColor" viewBox="0 0 24 24">
                        <path fill-rule="evenodd"
                              d="M12 .297c-6.63 0-12 5.373-12 12 0 5.302
                              3.438 9.8 8.205 11.385.6.113.82-.258.82-.577
                              0-.285-.01-1.04-.015-2.04-3.338.724-4.042-1.61-4.042-1.61-.546-1.387-1.333-1.757-1.333-1.757-1.089-.745.084-.729.084-.729
                              1.205.084 1.84 1.236 1.84 1.236 1.07 1.834 2.807 1.304
                              3.492.997.108-.776.418-1.305.762-1.605-2.665-.3-5.467-1.334-5.467-5.93
                              0-1.31.468-2.38 1.235-3.22-.135-.303-.54-1.523.105-3.176
                              0 0 1.005-.322 3.3 1.23a11.51 11.51 0 0 1 3-.405c1.02.005
                              2.045.138 3 .405 2.28-1.552 3.285-1.23 3.285-1.23.645 1.653.24
                              2.873.12 3.176.765.84 1.23 1.91 1.23 3.22 0 4.61-2.805
                              5.625-5.475 5.92.435.375.81 1.096.81 2.215
                              0 1.6-.015 2.887-.015 3.286 0 .315.21.69.825.57C20.565
                              22.092 24 17.592 24 12.297 24 5.67 18.627.297 12 .297z"/>
                    </svg>
                    <span class="ml-2 text-gray-700 font-medium">
                        @if(isSignUp)
                            Sign Up with GitHub
                        @else
                            Log in with GitHub
                        @endif
                    </span>
                </span>
            </a>
        </div>


        <div class="text-center mt-3">
            <p class="text-gray-600">

                @if(isSignUp)
                    Already have an account?
                    <a href="/login" class="text-emerald-500 hover:text-emerald-600">
                        Login here
                    </a>
                @else
                    Not registered?
                    <a href="/sign-up" class="text-emerald-500 hover:text-emerald-600">
                        Create an account
                    </a>
                @endif

            </p>
        </div>
    </div>
</div>
