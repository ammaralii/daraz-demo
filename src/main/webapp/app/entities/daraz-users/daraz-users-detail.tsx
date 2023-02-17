import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './daraz-users.reducer';

export const DarazUsersDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const darazUsersEntity = useAppSelector(state => state.darazUsers.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="darazUsersDetailsHeading">Daraz Users</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{darazUsersEntity.id}</dd>
          <dt>
            <span id="fullName">Full Name</span>
          </dt>
          <dd>{darazUsersEntity.fullName}</dd>
          <dt>
            <span id="email">Email</span>
          </dt>
          <dd>{darazUsersEntity.email}</dd>
          <dt>
            <span id="phone">Phone</span>
          </dt>
          <dd>{darazUsersEntity.phone}</dd>
          <dt>Manager</dt>
          <dd>{darazUsersEntity.manager ? darazUsersEntity.manager.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/daraz-users" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/daraz-users/${darazUsersEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default DarazUsersDetail;
