import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './addresses.reducer';

export const AddressesDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const addressesEntity = useAppSelector(state => state.addresses.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="addressesDetailsHeading">Addresses</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{addressesEntity.id}</dd>
          <dt>
            <span id="street">Street</span>
          </dt>
          <dd>{addressesEntity.street}</dd>
          <dt>
            <span id="city">City</span>
          </dt>
          <dd>{addressesEntity.city}</dd>
          <dt>
            <span id="state">State</span>
          </dt>
          <dd>{addressesEntity.state}</dd>
          <dt>User</dt>
          <dd>{addressesEntity.user ? addressesEntity.user.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/addresses" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/addresses/${addressesEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default AddressesDetail;
