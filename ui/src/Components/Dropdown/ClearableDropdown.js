import React from 'react'
import { Dropdown } from 'semantic-ui-react'

const ClearableDropdown = ({ placeholder, options, loading, onChange, selectedItems }) => {
    return (
        <Dropdown
            placeholder={placeholder}
            selection
            clearable
            options={options}
            loading={loading}
            onChange={onChange}
            value={selectedItems}
        />
    )
}

export default ClearableDropdown
