import { instance } from "../Instance"

export const getLanguages = () => {
    return instance.get("/languages/get-all")
}

export const getLanguageById = (id) => {
    return instance.get(`/languages/find-by-id/${id}`)
}