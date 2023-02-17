import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './roles.reducer';

export const RolesDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const rolesEntity = useAppSelector(state => state.roles.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="rolesDetailsHeading">Roles</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{rolesEntity.id}</dd>
          <dt>
            <span id="rolePrId">Role Pr Id</span>
          </dt>
          <dd>{rolesEntity.rolePrId}</dd>
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{rolesEntity.name}</dd>
          <dt>User</dt>
          <dd>
            {rolesEntity.users
              ? rolesEntity.users.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {rolesEntity.users && i === rolesEntity.users.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/roles" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/roles/${rolesEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default RolesDetail;
