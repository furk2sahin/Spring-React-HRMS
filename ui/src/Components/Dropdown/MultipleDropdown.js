import React from 'react'
import { Dropdown } from 'semantic-ui-react'

const MultipleDropdown = ({ placeholder, options, loading, onChange, selectedItems }) => {
    return (
        <Dropdown
            placeholder={placeholder}
            multiple
            search
            selection
            clearable
            options={options}
            loading={loading}
            onChange={onChange}
            value={selectedItems}
        />
    )
}

export default MultipleDropdown
