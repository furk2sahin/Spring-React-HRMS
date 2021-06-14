import React from 'react'
import { Button, Icon } from 'semantic-ui-react'

const SignUpButton = () => {
    return (
        <Button color="orange" animated='fade' >
            <Button.Content visible> Sign Up <Icon name="user" /></Button.Content>
            <Button.Content hidden><Icon name="user" /></Button.Content>
        </Button>
    )
}

export default SignUpButton
