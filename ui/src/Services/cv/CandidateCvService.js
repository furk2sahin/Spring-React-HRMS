import { instance } from "../instance/Instance"

export const createCandidateCv = (cv) => {
    instance.post("/candidate-cv/add", cv)
}

export const getCandidateCvs = () => {
    instance.get("/candidate-cv/get-all")
}

export const uploadCvPhoto = (cvId, file) => {
    instance.post(`/candidate-cv/update-photo/${cvId}`, file)
}

export const getCandidateCvById = (id) => {
    instance.get(`/candidate-cv/get-candidate-by-id/${id}`)
}