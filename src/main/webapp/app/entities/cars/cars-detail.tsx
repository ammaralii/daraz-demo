import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './cars.reducer';

export const CarsDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const carsEntity = useAppSelector(state => state.cars.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="carsDetailsHeading">Cars</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{carsEntity.id}</dd>
          <dt>
            <span id="caruid">Caruid</span>
          </dt>
          <dd>{carsEntity.caruid}</dd>
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{carsEntity.name}</dd>
        </dl>
        <Button tag={Link} to="/cars" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/cars/${carsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default CarsDetail;
