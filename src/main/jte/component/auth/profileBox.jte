@import freeapp.me.qrgenerator.entity.User
@import freeapp.me.qrgenerator.web.dto.UserResponseDto

@param UserResponseDto currentUser

<div class="max-w-xl mx-auto mt-10">
    <div class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
        <form
                hx-put="/user"
                hx-swap="outerHTML"
        >
            <div class="mb-4">
                <label class="block text-gray-700 text-sm font-bold mb-2" for="email">
                    이메일(아이디) *
                </label>
                <div class="appearance-none w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
                    ${currentUser.getEmail()}
                </div>
            </div>

            <div class="mb-4">
                <label class="block text-gray-700 text-sm font-bold mb-2" for="password">
                    비밀번호 *
                </label>

                @if(currentUser.getSignType() == User.SignType.EMAIL)
                    <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline"
                           id="password" type="password" name="password" placeholder="비밀번호를 입력해주세요." required>

                    <div class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
                        <p class="text-xs text-gray-600">
                            (영문 대/소문자, 숫자, 특수문자를 입력하세요. 특수문자 경우 !@#$%^&*()-_=+만 사용 가능)
                        </p>
                    </div>

                @else
                    <p class="text-xs text-gray-600">
                        (social 회원가입 유저는 비밀번호를 지원하지 않습니다)
                    </p>
                @endif

            </div>

            <div class="mb-4">
                <label class="block text-gray-700 text-sm font-bold mb-2" for="name">
                    이름 *
                </label>
                <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                       id="name" name="username" type="text" value="${currentUser.getUsername()}"
                       placeholder="별칭을 입력해주세요.">
            </div>

            <%--        @template.component.imageUploader(profileImg = currentUser.getProfileImg())--%>

            <div class="flex items-center justify-end">
                <button type="submit"
                        class="cursor-pointer bg-gray-700 hover:bg-black text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
                    수정
                </button>
            </div>
        </form>

        @template.component.auth.userDeleteModal()

        <div class="flex justify-end mt-3"
        >
            <div class="text-sm font-medium cursor-pointer text-red-500 py-2"
                 onclick="document.getElementById('user-delete-modal-overlay').classList.remove('hidden')"
            >
                회원 탈퇴를 하시겠습니까?
            </div>
        </div>
    </div>

</div>
