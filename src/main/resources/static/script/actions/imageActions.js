export function previewImageFile() {
    const imageUpload = document.getElementById('formImageFile');
    const file = imageUpload.files[0];
    const reader = new FileReader();

    reader.onload = function (e) {
        const imageDataUrl = e.target.result;
        const imagePreview = document.getElementById('uploadImagePreview');
        if (imagePreview) {
            imagePreview.src = imageDataUrl;
        } else {
            const imagePreviewReal = document.getElementById('uploadImagePreviewReal');
            imagePreviewReal.src = imageDataUrl;
        }
    };

    reader.readAsDataURL(file);
}