
document.addEventListener("DOMContentLoaded", function () {
    const dropArea = document.getElementById("drop-area");
    const fileInput = document.getElementById("thumbnail");
    const preview = document.getElementById("preview");
    const cancelUpload = document.getElementById("cancelUpload");


    // 업로드 취소
    cancelUpload.addEventListener('click', function(e) {
        initThumbnail()
    });
    
    // Prevent default drag behaviors
    ["dragenter", "dragover", "dragleave", "drop"].forEach(eventName => {
        dropArea.addEventListener(eventName, preventDefaults, false);
        document.body.addEventListener(eventName, preventDefaults, false);
    });

    // Highlight drop area when item is dragged over it
    ["dragenter", "dragover"].forEach(eventName => {
        dropArea.addEventListener(eventName, highlight, false);
    });

    ["dragleave", "drop"].forEach(eventName => {
        dropArea.addEventListener(eventName, unhighlight, false);
    });

    // Handle dropped files
    dropArea.addEventListener("drop", handleDrop, false);

    function preventDefaults(e) {
        e.preventDefault();
        e.stopPropagation();
    }

    function highlight() {
        dropArea.classList.add("bg-primary", "text-white");
    }

    function unhighlight() {
        dropArea.classList.remove("bg-primary", "text-white");
    }

    function handleDrop(e) {
        const dt = e.dataTransfer;
        const files = dt.files;

        handleFiles(files);
    }


    fileInput.addEventListener("change", function (e) {
        e.preventDefault(); // 기본 동작 방지
        const files = fileInput.files;

        handleFiles(files);
    });


    function handleFiles(files) {

        if(files.length != 1) {
        alert('썸네일은 1개만 업로드 가능합니다.')
        return
        }

        // 썸네일을 초기화
        preview.innerHTML = "";

        // 단 하나의 이미지 파일만 선택되도록 제한합니다.
        const file = files[0];
        if (file && file.type.startsWith("image/")) {
            const reader = new FileReader();
            reader.onload = function (e) {
                const img = document.createElement("img");
                img.classList.add("thumb");
                img.classList.add("w-100");
                img.style.maxHeight = '360px'
                img.style.objectFit = 'contain'
                img.src = e.target.result;
                preview.appendChild(img);
            };
            reader.readAsDataURL(file);
        }

        // **
        const filesListObj = new DataTransfer(); 
        filesListObj.items.add(file)
        // 파일 입력 필드에 선택된 파일 설정
        fileInput.files = filesListObj.files;
    }


    function initThumbnail() {
        fileInput.files =  new DataTransfer().files
        preview.innerHTML = ""
    }
    
  
    
    
    
});

function deleteThumbnail(id) {
    const check = confirm('정말로 삭제하시겠습니까?')
    if( !check ) return


    let fileInput = document.getElementById("thumbnail");
    let preview = document.getElementById('preview')
    let uploadBox = document.getElementById('upload-box')
  
    let data = {"id"	: id}
    let request = new XMLHttpRequest()
    
    request.open("DELETE", `/files/${id}`, true)
    request.setRequestHeader("Content-Type", "application/json")
    request.setRequestHeader("X-CSRF-TOKEN", csrfToken) // 💍
    request.send( JSON.stringify( data ) )

    
    request.onreadystatechange = function() {
      
      // 요청 성공 시,
      if( request.readyState == request.DONE && request.status == 200 ) {
        console.log( '파일 삭제 성공')
        // 파일 항목을 화면에서 제거
        if( request.responseText == 'SUCCESS' ) {
            fileInput.files = new DataTransfer().files
            preview.innerHTML = ""
            // uploadBox.style.display = 'block'
            uploadBox.classList = ''
        }
      }
    }
  }
