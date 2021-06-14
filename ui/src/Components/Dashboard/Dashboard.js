
import React, { useEffect, useState } from 'react'
import { Container, Dropdown, Grid, Segment } from 'semantic-ui-react'
import { getCities } from '../../Services/local/CityService'
import { findFacultyByUniversityId } from '../../Services/local/FacultyService'
import { getLanguages } from '../../Services/local/LanguageService'
import { findSectionByFacultyId } from '../../Services/local/SectionService'
import { findUniversityByCityId } from '../../Services/local/UniversityService'
import {
    initialCityState,
    initialFacultyState,
    initialLanguageState,
    initialSectionState,
    initialUniversityState
} from '../../Constants/Constants'

const Dashboard = () => {

    const [cityState, setCityState] = useState(initialCityState)

    const [universityState, setUniversityState] = useState(initialUniversityState)

    const [facultyState, setFacultyState] = useState(initialFacultyState)

    const [sectionState, setSectionState] = useState(initialSectionState)

    const [languageState, setLanguageState] = useState(initialLanguageState)

    useEffect(() => {
        const init = async () => {
            // get cities
            try {
                setCityState({ ...cityState, cityLoading: true })
                const { data } = await getCities();
                setCityState({
                    ...cityState,
                    cityOptions: data.data.map(city => ({ key: city.id, value: city.id, text: city.name })),
                    cityLoading: false
                })
            } catch (error) {
                console.log(error);
            }

            // get languages
            try {
                const { data } = await getLanguages()
                setLanguageState({
                    ...languageState,
                    languageOptions: data.data.map(language => ({ key: language.id, value: language.id, text: language.name })),
                    languageLoading: false
                })
            } catch (e) {
                console.log(e)
            }
        }
        init();
    }, [])

    useEffect(() => {
        const init = async () => {
            if (Boolean(cityState.selectedCity)) {
                try {
                    setUniversityState({ universityState, universityLoading: true })
                    const { data } = await findUniversityByCityId(cityState.selectedCity);
                    setUniversityState({
                        ...universityState,
                        universityOptions: data.data.map(university => ({ key: university.id, value: university.id, text: university.name })),
                        universityLoading: false
                    })
                } catch (e) {
                    console.log(e);
                }
            } else {
                setUniversityState({ ...universityState, selectedUniversity: null, universityOptions: [] })
            }
        }
        init();
    }, [cityState.selectedCity])

    useEffect(() => {
        const init = async () => {
            if (Boolean(universityState.selectedUniversity)) {
                try {
                    setFacultyState({ ...facultyState, facultyLoading: true })
                    const { data } = await findFacultyByUniversityId(universityState.selectedUniversity);
                    setFacultyState({
                        ...facultyState,
                        facultyOptions: data.data.map(faculty => ({ key: faculty.id, value: faculty.id, text: faculty.name })),
                        facultyLoading: false
                    })
                } catch (e) {
                    console.log(e);
                }
            } else {
                setFacultyState({ ...facultyState, facultyDisabled: true, facultyOptions: [], selectedFaculty: null })
            }
        }
        init();
    }, [universityState.selectedUniversity])

    useEffect(() => {
        const init = async () => {
            if (Boolean(facultyState.selectedFaculty)) {
                try {
                    setSectionState({ ...sectionState, sectionLoading: true })
                    const { data } = await findSectionByFacultyId(facultyState.selectedFaculty);
                    setSectionState({
                        ...sectionState,
                        sectionOptions: data.data.map(faculty => ({ key: faculty.id, value: faculty.id, text: faculty.name })),
                        sectionLoading: false
                    })

                } catch (e) {
                    console.log(e);
                }
            } else {
                setSectionState({ ...sectionState, sectionOptions: [], selectedSection: null, sectionDisabled: true })
            }
        }
        init();
    }, [facultyState.selectedFaculty])

    return (
        <Container className="main">
            <Segment>
                <Grid>
                    <Grid.Row>
                        <Dropdown
                            placeholder="City"
                            selection
                            clearable
                            search
                            searchQuery={cityState.cityInput}
                            onSearchChange={(e, { searchQuery }) => setCityState({ ...cityState, cityInput: searchQuery.toLocaleUpperCase() })}
                            options={cityState.cityOptions}
                            loading={cityState.cityLoading}
                            onChange={(e, { value }) => { setCityState({ ...cityState, cityInput: "", selectedCity: value }) }}
                            value={cityState.selectedCity}
                        />
                    </Grid.Row>
                    <Grid.Row>
                        <Dropdown
                            placeholder="Select University"
                            selection
                            clearable
                            search
                            options={universityState.universityOptions}
                            loading={universityState.universityLoading}
                            searchQuery={universityState.universityInput}
                            onSearchChange={(e, { searchQuery }) => setUniversityState({ ...universityState, universityInput: searchQuery.toLocaleUpperCase() })}
                            onChange={(e, { value }) => { setUniversityState({ ...universityState, selectedUniversity: value, universityInput: "" }) }}
                            disabled={!cityState.selectedCity}
                            value={universityState.selectedUniversity}
                        />
                    </Grid.Row>
                    <Grid.Row>
                        <Dropdown
                            placeholder="Select Faculty"
                            selection
                            clearable
                            search
                            options={facultyState.facultyOptions}
                            loading={facultyState.facultyLoading}
                            searchQuery={facultyState.facultyInput}
                            onSearchChange={(e, { searchQuery }) => setFacultyState({ ...facultyState, facultyInput: searchQuery.toLocaleUpperCase() })}
                            onChange={(e, { value }) => setFacultyState({ ...facultyState, selectedFaculty: value, facultyInput: "" })}
                            disabled={!universityState.selectedUniversity}
                            value={facultyState.selectedFaculty}
                        />
                    </Grid.Row>
                    <Grid.Row>
                        <Dropdown
                            placeholder="Select Section"
                            selection
                            clearable
                            search
                            options={sectionState.sectionOptions}
                            loading={sectionState.sectionLoading}
                            searchQuery={sectionState.sectionInput}
                            onSearchChange={(e, { searchQuery }) => setSectionState({ ...sectionState, sectionInput: searchQuery.toLocaleUpperCase() })}
                            onChange={(e, { value }) => { setSectionState({ ...sectionState, selectedSection: value, sectionInput: "" }) }}
                            disabled={!facultyState.selectedFaculty}
                            value={sectionState.selectedSection}
                        />
                    </Grid.Row>
                    <Grid.Row>
                        <Dropdown
                            placeholder="Language"
                            search
                            selection
                            clearable
                            options={languageState.languageOptions}
                            loading={languageState.loading}
                            onChange={(e, { value }) => setLanguageState({ ...languageState, selectedLanguage: value })}
                            value={languageState.selectedLanguage}
                        />
                    </Grid.Row>
                </Grid>
            </Segment>
        </Container>
    )
}

export default Dashboard
