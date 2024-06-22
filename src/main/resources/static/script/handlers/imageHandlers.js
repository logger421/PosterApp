import {previewImageFile} from "../actions/imageActions.js";

export function handleImagePreview() {
    previewImageFile();
}

window.handleImagePreview = handleImagePreview;