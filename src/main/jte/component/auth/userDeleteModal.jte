

<div id="user-delete-modal-overlay" class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50 hidden"
     onclick="if(event.target === this) { this.classList.add('hidden'); }">
    <div id="group-modal" class="bg-white rounded-lg shadow-lg w-full max-w-md p-6">
        <div class="flex justify-between items-center mb-4">
            <h3 class="text-lg text-red-500 font-medium">정말 탈퇴를 원하십니까?</h3>
            <button class="text-gray-500 hover:text-gray-700"
                    onclick="document.getElementById('user-delete-modal-overlay').classList.add('hidden')">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
                </svg>
            </button>
        </div>

        <form hx-delete="/user"
              hx-on--after-on-load="if(event.detail.successful) { document.getElementById('user-delete-modal-overlay').classList.add('hidden'); this.reset(); }">
            <div class="mb-4">
                <label for="delete-reason" class="block text-sm font-medium text-gray-700 mb-1">탈퇴 사유</label>
                <input type="text" id="delete-reason" name="reason" placeholder="탈퇴 이유를 입력하세요"
                       class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-indigo-500" required>
            </div>


            <div class="flex justify-end space-x-2">
                <button type="button" class="cursor-pointer px-4 py-2 border border-gray-300 rounded-md text-gray-700 hover:bg-gray-50"
                        onclick="document.getElementById('user-delete-modal-overlay').classList.add('hidden')">
                    취소
                </button>
                <button type="submit" class="cursor-pointer px-4 py-2 border border-gray-300 rounded-md text-gray-700 hover:bg-gray-50">
                    탈퇴
                </button>
            </div>
        </form>
    </div>
</div>
