import React from 'react'
import { Button, Icon } from 'semantic-ui-react'

const LogoutButton = () => {
    return (
        <Button color="red" animated='fade' >
            <Button.Content visible> Logout <Icon name="sign out" /></Button.Content>
            <Button.Content hidden><Icon name="sign out" /></Button.Content>
        </Button>
    )
}

export default LogoutButton
