const sideMenuButtons = document.querySelectorAll(".menu-btn");
const icons = document.querySelectorAll(".icon-wrapper i");
const userMenuWrapper = document.querySelector(".user-menu-wrapper");
const userMenuContent = document.querySelector(".user-menu-content");

// 목록
service.getBannerList(layout.showList);

// 사이드바 펼침/접힘
sideMenuButtons.forEach((menu) => {
    menu.addEventListener("click", function () {
        const submenu = this.nextElementSibling;
        const icon = this.querySelector(".icon-wrapper i");
        if (submenu && submenu.classList.contains("menu-sub-list")) {
            submenu.classList.toggle("show");
            if (submenu.classList.contains("show")) {
                icon.classList.remove("mdi-chevron-right");
                icon.classList.add("mdi-chevron-down");
            } else {
                icon.classList.remove("mdi-chevron-down");
                icon.classList.add("mdi-chevron-right");
            }
        }
    });
});

// 관리자 이메일 토글
userMenuWrapper.addEventListener("click", () => {
    userMenuContent.classList.toggle("show");
});

document.addEventListener("click", (e) => {
    if (
        !userMenuWrapper.contains(e.target) &&
        !userMenuContent.contains(e.target)
    ) {
        userMenuContent.classList.remove("show");
    }
});

// 파일 썸네일 관련
const input = document.getElementById("banner-file");
const previewContainer = document.getElementById("banner-img");
const MAX_FILES = 8;
const MAX_SIZE = 20 * 1024 * 1024;
let fileBuffer = [];
let deleteFileIds = [];

const toKey = (f) => `${f.name}|${f.size}|${f.lastModified}`;

const syncInput = () => {
    const dt = new DataTransfer();
    fileBuffer.forEach(f => {
        if (!f.existing) dt.items.add(f);
    });
    input.files = dt.files;
};

const render = () => {
    previewContainer.innerHTML = '';
    fileBuffer.forEach((file, idx) => {
        const item = document.createElement('div');
        item.className = 'preview-item';

        if (file.existing) {
            const img = document.createElement('img');
            img.className = 'preview-thumb';
            img.src = file.url;
            item.appendChild(img);
        }
        else if (file.type && file.type.startsWith('image/')) {
            const img = document.createElement('img');
            img.className = 'preview-thumb';
            const reader = new FileReader();
            reader.onload = (e) => { img.src = e.target.result; };
            reader.readAsDataURL(file);
            item.appendChild(img);
        }
        else if (!file.type.startsWith('image/')) {
            return;
        }
        // else {
        //     const box = document.createElement('div');
        //     box.className = 'preview-generic';
        //     box.textContent = file.name;
        //     item.appendChild(box);
        // }

        const rm = document.createElement("button");
        rm.type = 'button';
        rm.className = 'preview-remove';
        rm.innerHTML = `<svg width="14" height="14" viewBox="0 0 24 24" fill="none">
                          <path d="M18 6L6 18M6 6l12 12" stroke="currentColor" stroke-width="2" stroke-linecap="round"/>
                        </svg>`;
        rm.addEventListener('click', () => {
            if (file.existing && file.id) {
                deleteFileIds.push(file.id);
            }
            fileBuffer.splice(idx, 1);
            syncInput();
            render();
        });

        item.appendChild(rm);
        previewContainer.appendChild(item);
    });
};

const addFiles = (files) => {
    const existingKeys = new Set(fileBuffer.map(toKey));
    const arFile = Array.from(files);

    for (const f of arFile) {
        if (fileBuffer.length >= MAX_FILES) {
            alert(`최대 ${MAX_FILES}개까지 업로드할 수 있습니다.`);
            break;
        }
        if (f.size > MAX_SIZE) {
            alert(`"${f.name}" 파일이 용량 제한(20MB)을 초과했습니다.`);
            continue;
        }
        if (existingKeys.has(toKey(f))) {
            continue;
        }
        fileBuffer.push(f);
        existingKeys.add(toKey(f));
    }
    syncInput();
    render();
};
input.addEventListener('change', () => addFiles(input.files));


// 목록
const pagination = document.querySelector(".pagination.kok-pagination");
pagination.addEventListener("click", async (e) => {
    e.preventDefault();
    await service.getBannerList(layout.showList, e.target.dataset.page);

    // 페이지 번호
    const clickNum = e.target.closest("a[data-page]");
    const pageNumber = parseInt(clickNum.dataset.page);

    const pageNumsList = pagination.querySelectorAll("li.page-num");
    pageNumsList.forEach((pageNum) => {
        pageNum.classList.remove("active");
    });

    const currentList = Array.from(pageNumsList).find((pageNum) => {
        const activeList = pageNum.querySelector("a.page-item-num");
        return activeList && parseInt(activeList.dataset.page) === pageNumber;
    });

    if(currentList){
        currentList.classList.add("active");
    }
});