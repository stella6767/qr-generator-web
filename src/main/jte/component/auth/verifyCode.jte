@param String email
@param Long expireMinute
@param String token


<div class="flex-grow flex items-center justify-center w-full px-4">
    <div class="max-w-md mx-auto bg-white p-8 rounded-lg shadow-sm">
        <h1 class="text-2xl font-medium text-center mb-8">Account Verification</h1>

        <div class="flex items-start mb-6">
            <div class="flex-shrink-0 mt-1">
                <svg class="h-6 w-6 text-gray-700" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                          d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z"/>
                </svg>
            </div>
            <div class="ml-3">
                <p class="text-sm text-gray-700">
                    <span class="bg-gray-200 px-1">${email}</span> Please enter the verification code
                    sent to your email. The code is valid for ${expireMinute} minutes.
                </p>
            </div>
        </div>

        <div class="mb-6">
            <p class="text-sm text-gray-700 mb-3">Verification Code</p>

            <form hx-post="/verify-code"
                  hx-trigger="verify-code"
                  hx-swap="none"
                  hx-on--after-request="if(event.detail.successful) {
                            location.reload()
                        }"
                  oninput="collectAndSubmitCode(this)"
            >
                <div class="flex justify-between space-x-2">
                    <input type="text" id="code1" maxlength="1"
                           class="w-12 h-12 border border-gray-300 rounded text-center text-xl"
                           oninput="moveFocus(event, this, 'code2')"/>
                    <input type="text" id="code2" maxlength="1"
                           class="w-12 h-12 border border-gray-300 rounded text-center text-xl"
                           oninput="moveFocus(event, this, 'code3')"/>
                    <input type="text" id="code3" maxlength="1"
                           class="w-12 h-12 border border-gray-300 rounded text-center text-xl"
                           oninput="moveFocus(event, this, 'code4')"/>
                    <input type="text" id="code4" maxlength="1"
                           class="w-12 h-12 border border-gray-300 rounded text-center text-xl"
                           oninput="moveFocus(event, this, 'code5')"/>
                    <input type="text" id="code5" maxlength="1"
                           class="w-12 h-12 border border-gray-300 rounded text-center text-xl"
                           oninput="moveFocus(event, this, 'code6')"/>
                    <input type="text" id="code6" maxlength="1"
                           class="w-12 h-12 border border-gray-300 rounded text-center text-xl"
                           oninput="moveFocus(event, this, null)"/>

                </div>
                <input type="hidden" name="code" id="codeFull"/>
                <input type="hidden" id="token" name="token" value="${token}"/>

            </form>


        </div>


        <div class="text-sm text-gray-700 cursor-pointer"
             hx-post="/resend-code"
             hx-target="#resend-result"
             hx-ext="hx-dataset-include"
             data-email="${email}"
             data-token="${token}"
             hx-include="this"
        >
            <p class="text-blue-600">
                Resend Code
            </p>

            <p id="resend-result" class="text-gray-500">

            </p>

        </div>
    </div>

</div>
