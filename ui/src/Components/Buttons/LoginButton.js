import React from 'react'
import { Button, Icon } from 'semantic-ui-react'

const LoginButton = () => {
    return (
        <Button color="vk" animated='fade'>
            <Button.Content visible> Login <Icon name="sign in" /></Button.Content>
            <Button.Content hidden><Icon name="sign in" /></Button.Content>
        </Button>
    )
}

export default LoginButton
