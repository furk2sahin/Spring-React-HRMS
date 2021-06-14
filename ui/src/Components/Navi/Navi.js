import React from 'react'
import { Icon, Menu } from 'semantic-ui-react'
import AdvertiseButton from '../Buttons/AdvertiseButton'
import CreateCVButton from '../Buttons/CreateCVButton'
import LoginButton from '../Buttons/LoginButton'
import LogoutButton from '../Buttons/LogoutButton'
import SignUpButton from '../Buttons/SignUpButton'


const Navi = () => {
    return (
        <Menu inverted stackable fixed="top" color="teal">
            <Menu.Item header as="h4">
                <big className="first-letter">H</big>uman
                <big className="first-letter">R</big>esources
                <big className="first-letter">M</big>anagement
                <big className="first-letter">S</big>ystem
            </Menu.Item>
            <Menu.Item header as="h4">
                <Icon name="home" />
                Home
            </Menu.Item>
            <Menu.Item header as="h4">
                <Icon name="list alternate outline" />
                Job Advertisements
            </Menu.Item>


            <Menu.Menu position="right">
                <Menu.Item>
                    <LoginButton />
                </Menu.Item>
                <Menu.Item>
                    <SignUpButton />
                </Menu.Item>
                <Menu.Item>
                    <CreateCVButton />
                </Menu.Item>
                <Menu.Item>
                    <LogoutButton />
                </Menu.Item>
                <Menu.Item>
                    <AdvertiseButton />
                </Menu.Item>
            </Menu.Menu>
        </Menu>
    )
}

export default Navi
