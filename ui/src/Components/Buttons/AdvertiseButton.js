import React from 'react'
import { Button, Icon } from 'semantic-ui-react'

const AdvertiseButton = () => {
    return (
        <Button color="violet" animated='fade' >
            <Button.Content visible > Job Advertise <Icon name="mouse pointer" /></Button.Content>
            <Button.Content hidden><Icon name="mouse pointer" /></Button.Content>
        </Button>
    )
}

export default AdvertiseButton
