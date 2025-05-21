@import freeapp.me.qrgenerator.util.SecurityUtilsKt


<div class="navbar bg-gray-400 z-30 sticky top-0">
    <div class="flex-shrink-0">
        <a href="https://github.com/stella6767/qr-generator-web" target="_blank">
            <svg class="cursor-pointer" height="50" viewbox="0 0 98 96" width="50">
                <path clip-rule="evenodd"
                      d="M48.854 0C21.839 0 0 22 0 49.217c0 21.756 13.993 40.172 33.405 46.69 2.427.49 3.316-1.059 3.316-2.362 0-1.141-.08-5.052-.08-9.127-13.59 2.934-16.42-5.867-16.42-5.867-2.184-5.704-5.42-7.17-5.42-7.17-4.448-3.015.324-3.015.324-3.015 4.934.326 7.523 5.052 7.523 5.052 4.367 7.496 11.404 5.378 14.235 4.074.404-3.178 1.699-5.378 3.074-6.6-10.839-1.141-22.243-5.378-22.243-24.283 0-5.378 1.94-9.778 5.014-13.2-.485-1.222-2.184-6.275.486-13.038 0 0 4.125-1.304 13.426 5.052a46.97 46.97 0 0 1 12.214-1.63c4.125 0 8.33.571 12.213 1.63 9.302-6.356 13.427-5.052 13.427-5.052 2.67 6.763.97 11.816.485 13.038 3.155 3.422 5.015 7.822 5.015 13.2 0 18.905-11.404 23.06-22.324 24.283 1.78 1.548 3.316 4.481 3.316 9.126 0 6.6-.08 11.897-.08 13.526 0 1.304.89 2.853 3.316 2.364 19.412-6.52 33.405-24.935 33.405-46.691C97.707 22 75.788 0 48.854 0z"
                      fill="#fff" fill-rule="evenodd"></path>
            </svg>
        </a>
    </div>

    <a href="/">
        <div class="cursor-pointer absolute left-1/2 top-1/3 transform -translate-x-1/2 font-bold text-xl flex items-center">
            QR Generator
        </div>
    </a>

    <div class="flex grow justify-end px-2">
        <div class="flex items-stretch">

            @if(SecurityUtilsKt.isLoggedIn())

                <div class="dropdown dropdown-end">
                    <div tabindex="0" role="button" class="btn btn-ghost rounded-field">
                        <div class="w-8 rounded-full">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                                 stroke="currentColor" class="w-5 h-5">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      d="M15.75 6a3.75 3.75 0 1 1-7.5 0 3.75 3.75 0 0 1 7.5 0ZM4.501 20.118a7.5 7.5 0 0 1 14.998 0A17.933 17.933 0 0 1 12 21.75c-2.676 0-5.216-.584-7.499-1.632Z"/>
                            </svg>
                        </div>
                    </div>
                    <ul
                            tabindex="0"
                            class="menu dropdown-content bg-base-200 rounded-box z-1 mt-4 w-52 p-2 shadow-sm">
                        <li><a href="/profile">Profile</a></li>
                        <li><a href="/logout">Logout</a></li>
                    </ul>
                </div>

            @else

<%--                <div class="dropdown dropdown-end">--%>
<%--                    <div tabindex="0" role="button" class="btn btn-ghost rounded-field">--%>
<%--                        Guide--%>
<%--                    </div>--%>
<%--                    <ul--%>
<%--                            tabindex="0"--%>
<%--                            class="menu dropdown-content bg-base-200 rounded-box z-1 mt-4 w-52 p-2 shadow-sm">--%>
<%--                        <li><a href="/billing/plans">View all plans</a></li>--%>
<%--                    </ul>--%>
<%--                </div>--%>

                <a href="/login" class="btn btn-ghost rounded-field">Login</a>
                <a href="/sign-up" class="btn btn-ghost rounded-field">Sign Up</a>

            @endif
        </div>
    </div>
</div>


