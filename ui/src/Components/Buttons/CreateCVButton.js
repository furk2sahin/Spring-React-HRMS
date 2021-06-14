import React from 'react'
import { Button, Icon } from 'semantic-ui-react'

const CreateCVButton = () => {
    return (
        <Button color="green" animated='fade' >
            <Button.Content visible > Create CV <Icon name="clipboard outline" /></Button.Content>
            <Button.Content hidden><Icon name="clipboard outline" /></Button.Content>
        </Button>
    )
}

export default CreateCVButton
