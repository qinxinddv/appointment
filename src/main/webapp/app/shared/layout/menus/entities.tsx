import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Translate, translate } from 'react-jhipster';
import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown
    icon="th-list"
    name={translate('global.menu.entities.main')}
    id="entity-menu"
    style={{ maxHeight: '80vh', overflow: 'auto' }}
  >
    <MenuItem icon="asterisk" to="/sys-dict">
      <Translate contentKey="global.menu.entities.sysDict" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/appointment">
      <Translate contentKey="global.menu.entities.appointment" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/community">
      <Translate contentKey="global.menu.entities.community" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/appointment-config">
      <Translate contentKey="global.menu.entities.appointmentConfig" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/appointment-pool">
      <Translate contentKey="global.menu.entities.appointmentPool" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/black-key">
      <Translate contentKey="global.menu.entities.blackKey" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/org">
      <Translate contentKey="global.menu.entities.org" />
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
