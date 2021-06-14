
import instance from '../instance/Instance'

export const createCandidateLanguage = (language) => {
    return instance.post("/candidate-language", language)
}

export const getCandidateLanguages = () => {
    return instance.get("/candidate-language/get-all")
}